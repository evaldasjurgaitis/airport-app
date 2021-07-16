import React from 'react';
import { Table as TableBase } from 'antd';

import 'antd/lib/grid/style/index.less';

interface Props {
    loading?: boolean;
    columns: any;
    rows: any;
    pagination?: any;
    onChange?: (pagination: any) => void;
}

const Table = ({ loading, columns, rows, pagination, onChange }: Props) => {
    return (
        <TableBase columns={columns} dataSource={rows} loading={loading} pagination={pagination} onChange={onChange} />
    );
};

export default Table;
